package oslomet.testing;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
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
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestKontoController {

    @InjectMocks
    private AdminKontoController adminKontoController; //skal testes

    @Mock
    private AdminRepository repository; //skal mockes

    @Mock
    private Sikkerhet sjekk;

    //Testing av hent alle konti, innlogget OK

    @Test
    public void hentAlleKontiInnlogget(){

        //arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon transaksjonEn = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");

        Transaksjon transaksjonTo = new Transaksjon(2, "105010123456",
                135.9, "2023-03-06", "OneCall", "1", "105020123456");

        transaksjonList.add(transaksjonEn);
        transaksjonList.add(transaksjonTo);

        List<Konto> kontoList = new ArrayList<>();

        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);
        kontoList.add(enKonto);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.hentAlleKonti()).thenReturn(kontoList);

        //act
        List<Konto> resultat = adminKontoController.hentAlleKonti();

        //assert
        assertEquals(kontoList, resultat);
    }

    //Testing av hent all konti, ikke innlogget
    @Test
    public void hentAlleKontiIkkeInnlogget(){

        //arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        List<Konto> resultat = adminKontoController.hentAlleKonti();

        //assert
        assertNull(resultat);
    }

    //Testing av registrer konto, innlogget
    @Test
    public void registrerKontoInnlogget(){

        //arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon transaksjonEn = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");



        transaksjonList.add(transaksjonEn);


        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.registrerKonto(enKonto)).thenReturn("OK");

        //act
        String resultat = adminKontoController.registrerKonto(enKonto);

        //assert
        assertEquals("OK", resultat);

    }

    //Test av registrer konto, ikke innlogget
    @Test
    public void registererKontoIkkeInnlogget(){
        //arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon transaksjonEn = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");

        transaksjonList.add(transaksjonEn);

        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);

        when(sjekk.loggetInn()).thenReturn(null);


        //act
        String resultat = adminKontoController.registrerKonto(enKonto);

        //assert
        assertEquals("Ikke innlogget", resultat);
    }

    //Test av endrekonto, innlogget
    @Test
    public void endreKontoInnlogget(){
        //arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon enTransaksjon = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");

        transaksjonList.add(enTransaksjon);

        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.endreKonto(any(Konto.class))).thenReturn("OK");

        //act
        String resultat = adminKontoController.endreKonto(enKonto);

        //assert
        assertEquals("OK", resultat);
    }

    //Test av endre konto, ikke innlogget
    @Test
    public void endreKontoIkkeInnlogget(){
        //arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon enTransaksjon = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");

        transaksjonList.add(enTransaksjon);

        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKontoController.endreKonto(enKonto);

        //assert
        assertEquals("Ikke innlogget", resultat);
    }

    //Test av slett konto, innlogget
    @Test
    public void slettKontoInnlogget(){
        //Arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon enTransaksjon = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");

        transaksjonList.add(enTransaksjon);

        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);

        when(sjekk.loggetInn()).thenReturn("105010123456");
        when(repository.slettKonto(anyString())).thenReturn("OK");

        //act
        String resultat = adminKontoController.slettKonto(enKonto.getKontonummer());

        //assert
        assertEquals("OK", resultat);

    }

    //Test av slett konto, ikke innlogget
    @Test
    public void slettKontoIkkeInnlogget(){
        //Arrange
        List<Transaksjon>transaksjonList = new ArrayList<>();

        Transaksjon enTransaksjon = new Transaksjon(1, "105010123456",
                275.5, "2023-03-05", "Telia", "1", "105020123456");

        transaksjonList.add(enTransaksjon);

        Konto enKonto = new Konto("01010110523","105010123456", 400, "Lønnskonto", "NOK", transaksjonList);

        when(sjekk.loggetInn()).thenReturn(null);

        //act
        String resultat = adminKontoController.slettKonto(enKonto.getKontonummer());

        //assert
        assertEquals("Ikke innlogget", resultat);
    }

}
