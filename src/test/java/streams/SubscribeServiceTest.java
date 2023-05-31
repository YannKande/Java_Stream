package streams;

import custumer.Customers;

import mapper.MapperMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;


class SubscribeServiceTest {

    List<Customers> mock;

    @BeforeEach
    void setup() throws IOException {

        mock=MapperMock.getCustomers();
    }

    @Test
    void getSubscriptionTest() throws IOException {


        List<Customers>customersSubscribes= new ArrayList<>(mock);
        customersSubscribes.forEach(System.out::println);
        assertNotNull(mock);
    }

    @Test
    void getCustomersActiveSubscribesTest() throws IOException {

        Predicate<Customers> activeCustomers= activecustomers ->
                activecustomers.getAvailability().equals("active");

        List<Customers>expectedList=mock.stream()
                .filter(activeCustomers)
                .collect(Collectors.toList());

        expectedList.forEach(System.out::println);
        assertNotNull(expectedList);

    }

    @Test
     void getCountCustomers() throws  Exception{

        List<Customers> customers=MapperMock.getCustomers();
        Long max= (long) customers.size();
        assertEquals(3,max);

    }

    @Test
    @DisplayName("extract all customers family assurance")
    void getCustomersFamilyServicesTest(){

        //method one with predicate methode
        Predicate<Customers> familyPackage=
                fm-> fm.services.mypackage.equals("family");

       List<Customers>expectedFamilyPackage=mock.stream()
               .filter(familyPackage)
               .collect(Collectors.toList());

       expectedFamilyPackage.forEach(System.out::println);

       //method two
        List<Customers>expectedFamilyPackage2=mock.stream()
                        .filter(ffm->ffm.services.mypackage.equals("family"))
                                .collect(Collectors.toList());

        expectedFamilyPackage2.forEach(System.out::println);

       assertNotNull(expectedFamilyPackage);
        assertNotNull(expectedFamilyPackage2);

        assertSame(expectedFamilyPackage.size(), expectedFamilyPackage2.size());
    }

    @Test
    void getCostumersMycarPremiumPackage(){

        List<Customers> myCarServes= mock.stream()
                .filter(mcs->mcs.services.mypackage.equals("mycar"))
                .collect(Collectors.toList());

        myCarServes.forEach(System.out::println);

        assertNotNull(myCarServes);

    }

    @Test
    void shouldCustomerEmailNotFound(){

        Optional<Customers> expectException=
                mock.stream()
                        .filter(f->f.email.equals(""))
                        .findAny();

      assertFalse(expectException.isPresent());



    }

    @Test
    void getMinPackagePriceTest() {

        Double minPackagePrice = mock.stream()
                .mapToDouble(customers -> customers.services.getPrice())
                .min().orElse(0.0);
        assertEquals(12.0, minPackagePrice);
    }

         @Test
        void getMinPackageSubscribeTest(){
        Double maxPackageSubscribe=mock.stream()
                .mapToDouble( C->C.services.getPrice())
                .max().orElse(0);

        assertEquals(45.0,maxPackageSubscribe);

        }

        @Test
       void getScribeSumPackageTest(){

        Double sum=mock.stream()
                .mapToDouble(P->P.services.getPrice())
                .sum();
        assertEquals(72.0,sum);

        }

    @Test
    void getScribeAverageAmountPackageTest(){

        Double average=mock.stream()
                .mapToDouble(A->A.services.getPrice())
                .average().orElse(0.0);

        assertEquals(24.0,average);

    }


}
