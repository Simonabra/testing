package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

    @RunWith(MockitoJUnitRunner.class)
    public class EnhetstestSikkerhet {

        @InjectMocks
        // denne skal testes
        private BankController bankController;

        @Mock
        // denne skal Mock'es
        private BankRepository repository;

        @Mock
        // denne skal Mock'es
        private Sikkerhet sjekk;

        @Test
        public void sjekkLoggInn(){

        }

        @Test
        public void loggUt(){

        }

        @Test
        public void loggInnAdmin(){

        }

        @Test
        public void loggetInn(){

        }
}
