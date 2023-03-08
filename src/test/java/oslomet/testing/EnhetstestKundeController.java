package oslomet.testing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestKundeController {

    @InjectMocks
    private AdminKundeController adminKundeController; //skal testes

    @Mock
    private AdminRepository rep; //skal mockes

    @Mock
    private Sikkerhet sjekk; //skal mockes

    //test av hent alle kunder, innlogget

    @Test
    public void hentAlleKunderInnlogget(){

        //arrange
        List<Kunde>kundeList = new ArrayList<>();

        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22",
                "3270", "Asker", "22224444", "HeiHei");

        kundeList.add(enKunde);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(rep.hentAlleKunder()).thenReturn(kundeList);

        //act
        List<Kunde>resultat = adminKundeController.hentAlle();

        //assert
        assertEquals(kundeList, resultat);


    }

    //test av hent alle kunder, ikke innlogget

    @Test
    public void hentAlleKunderIkkeInnlogget(){

        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Kunde>resultat = adminKundeController.hentAlle();

        //assert
        assertNull(resultat);
    }

    //test av lagrekunde, innlogget

    @Test
    public void lagreKundeInnlogget(){

        //arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22",
                "3270", "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(rep.registrerKunde(enKunde)).thenReturn("OK");

        //act
        String resultat = adminKundeController.lagreKunde(enKunde);

        //assert
        assertEquals("OK", resultat);
    }

    //test av lagre kunde, ikke innlogget

    @Test
    public void lagreKundeIkkeInnlogget(){

        //arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22",
                "3270", "Asker", "22224444", "HeiHei");
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKundeController.lagreKunde(enKunde);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }

    //test av endre kunde, innlogget

    @Test
    public void endreKundeInnlogget(){
        //arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22",
                "3270", "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(rep.endreKundeInfo(enKunde)).thenReturn("OK");

        //act
        String resultat = adminKundeController.endre(enKunde);

        //assert
        assertEquals("OK", resultat);
    }

    //test av endre kunde, ikke innlogget

    @Test
    public void endreKundeIkkeInnlogget(){
        //arrange
        Kunde enKunde = new Kunde("01010110523", "Lene", "Jensen", "Askerveien 22",
                "3270", "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKundeController.endre(enKunde);

        //assert
        assertEquals("Ikke logget inn", resultat);
    }

    //Test av slett kunde, innlogget

    @Test
    public void slettKundeInnlogget(){
        //arrange
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(rep.slettKunde("01010110523")).thenReturn("OK");

        //act
        String resultat = adminKundeController.slett("01010110523");

        //assert
        assertEquals("OK", resultat);

    }

    //Test av slett kunde, innlogget

    @Test
    public void slettKundeIkkeInnlogget(){
        //arrange
        when(sjekk.loggetInn()).thenReturn(null);


        //act
        String resultat = adminKundeController.slett("01010110523");

        //assert
        assertEquals("Ikke logget inn", resultat);

    }


}
