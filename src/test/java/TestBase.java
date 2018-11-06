import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestBase {
    protected static Injector injector = Guice.createInjector(new GuiceInjector());

}