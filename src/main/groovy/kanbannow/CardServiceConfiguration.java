package kanbannow;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.db.DatabaseConfiguration;
import kanbannow.core.Template;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class CardServiceConfiguration extends Configuration {
    @NotEmpty
    String template;

    @NotEmpty
    String defaultName = "Stranger";

    public Template buildTemplate() {
        return new Template(template, defaultName);
    }



    @Valid
    @NotNull
    @JsonProperty("database")
    DatabaseConfiguration databaseConfiguration = new DatabaseConfiguration();


    public DatabaseConfiguration getDatabaseConfiguration() {
        return databaseConfiguration;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }
}
