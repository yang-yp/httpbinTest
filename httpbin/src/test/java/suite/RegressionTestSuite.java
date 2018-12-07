package suite;

import apiTest.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        HttpbinGetTest.class,
        HttpbinPostTest.class,
        HttpbinPutTest.class,
        HttpbinDeleteTest.class,
        HttpbinPatchTest.class
})
public class RegressionTestSuite {
   public static final Logger LOG = LoggerFactory.getLogger(RegressionTestSuite.class);

   @BeforeClass
    public static void setUpClass() {
       LOG.info("Start running OpenApi services regression test");
   }
    @AfterClass
    public static void tearDownClass(){
        LOG.info("OpenApi services regression test is completed");
    }

}
