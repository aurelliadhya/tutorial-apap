package apap.tutorial.gopud.service;
import apap.tutorial.gopud.model.MenuModel;
import apap.tutorial.gopud.repository.MenuDb;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

// menentukan library yang digunakan untuk menjalankan testing
@RunWith(MockitoJUnitRunner.class)
public class MenuServiceImplTest {
    @InjectMocks
    MenuService menuService = new MenuServiceImpl();
    @Mock
    MenuDb menuDb;

    // memberitahukan kepada TestRunner bahwa method tersebut adalah Test
//    @Test
//    public void whenAddValidRestoranItShouldCallRestoranRepositorySave() {
//        RestoranModel newRestoran = new RestoranModel();
//        newRestoran.setNama("mekdi");
//        newRestoran.setAlamat("pacil");
//        newRestoran.setNomorTelepon(14045);
//        restoranService.addRestoran(newRestoran);
//        verify(restoranDb, times(1)).save(newRestoran);
//    }
    @Test
    public void whenAddValidMenuItShouldCallMenuRepositorySave() {
        
    }
}
