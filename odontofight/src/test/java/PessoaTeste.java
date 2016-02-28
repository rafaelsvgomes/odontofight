import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.odontofight.entidade.Cliente;

public class PessoaTeste {

    private static Validator validator;

    @BeforeClass
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
		
    }

    @Test
    public void test() {
        Cliente p = new Cliente();
        p.setDescEmail("rafaelsvgomes@gmail.com");

        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(p);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Nome � obrigat�rio", constraintViolations.iterator().next().getMessage());
    }

    @Test
    public void testNomeMin() {
        Cliente p = new Cliente();
        p.setNomePessoa("Rafa");
        p.setDescEmail("rafaelsvgomes@gmail.com");

        Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(p);
        Assert.assertEquals(1, constraintViolations.size());
        Assert.assertEquals("Nome deve conter no m�nimo 5 caracteres", constraintViolations.iterator().next().getMessage());
    }
}
