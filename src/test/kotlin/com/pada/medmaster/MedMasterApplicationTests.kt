import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.test.util.TestPropertyValues
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.TestPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DirtiesContext
@ContextConfiguration(initializers = [MedMasterApplicationTests.TestEnvInitializer::class])
@TestPropertySource(properties = ["spring.datasource.driver-class-name="])
class MedMasterApplicationTests {

	@Test
	fun testFakeData() {
		// Your test logic
	}

	internal class TestEnvInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
		override fun initialize(applicationContext: ConfigurableApplicationContext) {
			val values = TestPropertyValues.of(
				"spring.datasource.url=" + POSTGRES_DB.jdbcUrl,
				"spring.datasource.password=" + POSTGRES_DB.password,
				"spring.datasource.username=" + POSTGRES_DB.username
			)
			values.applyTo(applicationContext)
		}
	}

	companion object {
		@Container
		private val POSTGRES_DB: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:12.8")
			.withDatabaseName("testdb")
			.withUsername("postgres")
			.withPassword("postgres")
	}
}
