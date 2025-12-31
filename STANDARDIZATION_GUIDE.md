# Guia de Padronização de Qualidade - Synaptra Projects

**Autor:** Leandro Marques  
**Versão:** 1.0.0  
**Data:** 2024

---

## 📋 Visão Geral

Este guia documenta as práticas de padronização aplicadas ao projeto `synaptra-ai-log-lib` e serve como referência para aplicar os mesmos padrões em outros serviços da organização.

---

## 🎯 Objetivos da Padronização

1. **Consistência**: Garantir que todos os projetos sigam os mesmos padrões
2. **Manutenibilidade**: Facilitar a compreensão e manutenção do código
3. **Documentação**: Melhorar a documentação através de Javadoc completo
4. **Qualidade**: Estabelecer padrões de qualidade de código
5. **Organização**: Estruturar arquivos de configuração de forma clara

---

## 📝 1. Javadoc em Inglês

### 1.1 Padrão de Documentação

Todas as classes, interfaces, métodos públicos e campos importantes devem ter Javadoc completo em inglês.

#### Estrutura do Javadoc de Classe:

```java
/**
 * Brief description of the class (one sentence).
 * 
 * <p>Optional detailed description paragraph.
 * 
 * <p>Additional paragraphs can be added for more context.
 * 
 * <p>The class provides:
 * <ul>
 *   <li>Feature 1</li>
 *   <li>Feature 2</li>
 * </ul>
 * 
 * <p>Example usage:
 * <pre>
 * {@code
 * // code example
 * }
 * </pre>
 * 
 * @author Leandro Marques
 * @version 1.0.0
 * @see RelatedClass
 * @since 1.0.0
 */
```

#### Estrutura do Javadoc de Método:

```java
/**
 * Brief description of the method (one sentence).
 * 
 * <p>Optional detailed description explaining:
 * <ul>
 *   <li>What the method does</li>
 *   <li>Important behavior or side effects</li>
 * </ul>
 * 
 * @param paramName description of the parameter
 * @return description of the return value
 * @throws ExceptionType when this exception is thrown
 */
```

#### Estrutura do Javadoc de Campo:

```java
/**
 * Brief description of the field.
 * 
 * <p>Optional additional details about the field's purpose or usage.
 */
private final Type fieldName;
```

### 1.2 Tags Obrigatórias

- **@author**: Sempre incluir "Leandro Marques"
- **@version**: Versão do projeto (ex: "1.0.0")
- **@since**: Versão em que foi introduzido (geralmente igual ao @version)

### 1.3 Tags Opcionais (quando aplicável)

- **@see**: Referências a classes/métodos relacionados
- **@param**: Para métodos com parâmetros
- **@return**: Para métodos com retorno
- **@throws**: Para métodos que lançam exceções
- **@deprecated**: Para APIs obsoletas

### 1.4 Exemplo Prático

**Antes:**
```java
@Configuration
public class ObjectMapperConfig {
  @Bean
  ObjectMapper objectMapper() {
    // ...
  }
}
```

**Depois:**
```java
/**
 * Configuration class for Jackson ObjectMapper bean.
 * 
 * <p>This configuration provides a customized ObjectMapper instance with:
 * <ul>
 *   <li>Automatic module discovery and registration</li>
 *   <li>Disabled failure on empty beans</li>
 * </ul>
 * 
 * @author Leandro Marques
 * @version 1.0.0
 * @since 1.0.0
 */
@Configuration
public class ObjectMapperConfig {
  /**
   * Creates and configures a Jackson ObjectMapper bean.
   * 
   * @return configured ObjectMapper instance
   */
  @Bean
  ObjectMapper objectMapper() {
    // ...
  }
}
```

---

## 📦 2. Organização do pom.xml

### 2.1 Estrutura Padrão

O `pom.xml` deve seguir esta ordem exata:

1. **Model Version**
2. **Project Coordinates** (groupId, artifactId, version, packaging)
3. **Project Information** (name, description)
4. **Properties** (organizadas por categoria)
5. **Dependency Management** (BOMs)
6. **Dependencies** (organizadas por categoria)
7. **Build** (plugins)

### 2.2 Seções com Comentários Explicativos

Cada seção principal deve ter um comentário de cabeçalho:

```xml
<!-- ====================================================================== -->
<!-- Section Name -->
<!-- ====================================================================== -->
```

### 2.3 Organização de Dependencies

As dependências devem ser agrupadas por categoria:

```xml
<!-- ================================================================== -->
<!-- Category Name -->
<!-- ================================================================== -->
<!-- Brief explanation of why this dependency is needed -->
<dependency>
    <!-- ... -->
</dependency>
```

### 2.4 Categorias de Dependencies

1. **Logging Dependencies**: Log4j, SLF4J, etc.
2. **Spring Framework Dependencies**: Spring Boot starters
3. **Distributed Tracing Dependencies**: OpenTelemetry, Micrometer
4. **JSON Processing Dependencies**: Jackson
5. **Utility Dependencies**: Lombok, Apache Commons, etc.
6. **Testing Dependencies**: JUnit, Mockito, etc. (se aplicável)

### 2.5 Properties Organizadas

As properties devem ser agrupadas logicamente:

```xml
<properties>
    <!-- Java Version Configuration -->
    <java.version>21</java.version>
    
    <!-- Spring Boot Version -->
    <spring.boot.version>3.5.6</spring.boot.version>
    
    <!-- Third-Party Library Versions -->
    <opentelemetry.version>1.57.0</opentelemetry.version>
    <lombok.version>1.18.36</lombok.version>
</properties>
```

### 2.6 Exemplo de Estrutura Completa

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project>
    <!-- ====================================================================== -->
    <!-- Maven Model Version -->
    <!-- ====================================================================== -->
    <modelVersion>4.0.0</modelVersion>

    <!-- ====================================================================== -->
    <!-- Project Coordinates -->
    <!-- ====================================================================== -->
    <groupId>com.ducks.synaptra</groupId>
    <artifactId>project-name</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <!-- ====================================================================== -->
    <!-- Project Information -->
    <!-- ====================================================================== -->
    <name>Project Name</name>
    <description>Project description</description>

    <!-- ====================================================================== -->
    <!-- Build Properties -->
    <!-- ====================================================================== -->
    <properties>
        <!-- ... -->
    </properties>

    <!-- ====================================================================== -->
    <!-- Dependency Management (BOMs) -->
    <!-- ====================================================================== -->
    <dependencyManagement>
        <!-- ... -->
    </dependencyManagement>

    <!-- ====================================================================== -->
    <!-- Dependencies -->
    <!-- ====================================================================== -->
    <dependencies>
        <!-- ... -->
    </dependencies>

    <!-- ====================================================================== -->
    <!-- Build Configuration -->
    <!-- ====================================================================== -->
    <build>
        <!-- ... -->
    </build>
</project>
```

---

## 🔧 3. Padrões de Código

### 3.1 Nomenclatura

- **Classes**: PascalCase (ex: `ObjectMapperConfig`)
- **Métodos**: camelCase (ex: `objectMapper()`)
- **Constantes**: UPPER_SNAKE_CASE (ex: `SERVICE_NAME`)
- **Variáveis**: camelCase (ex: `objectMapper`)

### 3.2 Organização de Imports

Os imports devem ser organizados nesta ordem:

1. Imports do mesmo pacote
2. Imports de outros pacotes do projeto
3. Imports de bibliotecas de terceiros
4. Imports do Java standard library

Exemplo:
```java
package com.ducks.synaptra.config;

import com.ducks.synaptra.properties.SynaptraLogProperties;
import io.opentelemetry.api.OpenTelemetry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
```

### 3.3 Comentários no Código

- Use Javadoc para documentação pública
- Use comentários inline apenas quando necessário para explicar lógica complexa
- Evite comentários óbvios que apenas repetem o código

---

## 📋 4. Checklist de Aplicação

Use este checklist ao padronizar um novo projeto:

### 4.1 Javadoc
- [ ] Todas as classes públicas têm Javadoc completo
- [ ] Todos os métodos públicos têm Javadoc
- [ ] Campos importantes têm Javadoc
- [ ] Javadoc está em inglês
- [ ] Tags @author, @version, @since estão presentes
- [ ] Exemplos de uso quando aplicável

### 4.2 pom.xml
- [ ] Estrutura segue a ordem padrão
- [ ] Seções têm comentários de cabeçalho
- [ ] Dependencies estão organizadas por categoria
- [ ] Cada dependency tem comentário explicativo
- [ ] Properties estão organizadas logicamente
- [ ] Plugins têm comentários explicativos

### 4.3 Código
- [ ] Nomenclatura segue os padrões
- [ ] Imports estão organizados
- [ ] Código está limpo e bem formatado
- [ ] Não há código comentado desnecessário

---

## 🚀 5. Passos para Aplicar em Outros Serviços

### Passo 1: Preparação
1. Faça backup do projeto atual
2. Crie uma branch para as mudanças: `git checkout -b feature/standardization`

### Passo 2: Javadoc
1. Identifique todas as classes públicas
2. Adicione Javadoc seguindo o padrão definido
3. Adicione Javadoc para métodos públicos
4. Adicione Javadoc para campos importantes
5. Verifique se todas as tags obrigatórias estão presentes

### Passo 3: pom.xml
1. Reorganize o pom.xml seguindo a estrutura padrão
2. Adicione comentários de seção
3. Organize dependencies por categoria
4. Adicione comentários explicativos para cada dependency
5. Organize properties logicamente

### Passo 4: Validação
1. Execute `mvn clean compile` para verificar se compila
2. Execute `mvn javadoc:javadoc` para gerar Javadoc e verificar erros
3. Revise o código gerado
4. Execute testes: `mvn test`

### Passo 5: Commit
1. Commit das mudanças: `git commit -m "chore: standardize code quality and documentation"`
2. Push da branch: `git push origin feature/standardization`
3. Crie Pull Request para revisão

---

## 📚 6. Recursos Adicionais

### 6.1 Ferramentas Úteis

- **Maven Javadoc Plugin**: Gera documentação HTML
  ```xml
  <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-javadoc-plugin</artifactId>
  </plugin>
  ```

- **Checkstyle**: Validação de padrões de código
- **SpotBugs**: Análise estática de código
- **PMD**: Detecção de problemas de código

### 6.2 Referências

- [Oracle Javadoc Guide](https://www.oracle.com/technical-resources/articles/java/javadoc-tool.html)
- [Maven POM Reference](https://maven.apache.org/pom.html)
- [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

---

## ✅ 7. Exemplo Completo

Veja o projeto `synaptra-ai-log-lib` como referência completa de todos os padrões aplicados.

### Arquivos de Referência:
- `pom.xml`: Estrutura completa e organizada
- `src/main/java/com/ducks/synaptra/annotation/EnableSynaptraLog.java`: Javadoc de anotação
- `src/main/java/com/ducks/synaptra/config/SynaptraLogAutoConfiguration.java`: Javadoc de configuração
- `src/main/java/com/ducks/synaptra/log/LogTracerImpl.java`: Javadoc de implementação

---

## 📞 8. Suporte

Para dúvidas ou sugestões sobre este guia, entre em contato com:
- **Autor**: Leandro Marques
- **Versão do Guia**: 1.0.0

---

**Última atualização**: 2024

