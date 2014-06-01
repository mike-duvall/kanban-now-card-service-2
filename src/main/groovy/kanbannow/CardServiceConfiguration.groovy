package kanbannow;

import kanbannow.core.Template;
import com.yammer.dropwizard.config.Configuration
import org.hibernate.validator.constraints.NotEmpty

public class CardServiceConfiguration extends Configuration {
    @NotEmpty
    String template;

    @NotEmpty
    String defaultName = "Stranger";

//    @Valid
//    @NotNull
//    @JsonProperty("database")
//    DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();
//
    public Template buildTemplate() {
        return new Template(template, defaultName);
    }
//
//    public DatabaseConfiguration getDatabaseConfiguration() {
//        return databaseConfiguration;
//    }

}
