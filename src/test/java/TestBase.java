import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;

public class TestBase {
    protected static Injector injector = Guice.createInjector(new GuiceInjector());

    @Before
    public void setup () {
        injector.injectMembers(this);
    }
}