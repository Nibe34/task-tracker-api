package tasktracker.backend;


import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;



class ModulithArchitectureTest {

    ApplicationModules modules = ApplicationModules.of(BackendApplication.class);

    @Test
    void shouldBeCompliantWithModulithRules() {
        modules.verify();
    }

    @Test
    void shouldGenerateDocumentation() throws Exception {
        new Documenter(modules)
            .writeDocumentation()
            .writeIndividualModulesAsPlantUml();
    }

}
