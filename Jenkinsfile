node("swarm") {
    checkout scm
    def log = new com.sap.ms.L()
    withCredentials(
                    [usernamePassword(credentialsId: 'OpenApiCreds',
                        passwordVariable: 'OPENAPI_PASSWORD',
                        usernameVariable: 'OPENAPI_USERNAME')
                    ]
                    )
    {
        def userInput =
            timeout(time: 5, unit: 'MINUTES'){
            input (
                id: "userInput",
                message: 'Please choose environment, browsers and test suite to run',
                parameters: [[$class: 'ChoiceParameterDefinition',
                choices: 'https://qa.aws.ariba.com/\nhttps://perf.aws.ariba.com/\nhttps://itg.aws.ariba.com/\nhttps://e2e.aws.ariba.com/\nhttps://tls.aws.ariba.com/\nNA',
                description: '',
                name: 'ServiceURL'],
                [$class: 'ChoiceParameterDefinition',
                choices: 'regressionTest\nselectedTest',
                description: '',
                name: 'TestSuite'],
                ])
            }

        def command2 = "./gradlew -Dopenapi.url=" + userInput['ServiceURL'] + " clean openapi:" + userInput['TestSuite']
        sh returnStatus: true, script: "set +e; ${command2}"

        archive (includes: 'openapi/reports/**')
        mail (to: 'DL_570D69605F99B78780000033@exchange.sap.corp',
                  subject: "OpenAPI BE Regression Test Report - Build ${env.BUILD_NUMBER}",
                  body: """
                  Jenkins Job: ${env.JOB_NAME}
                  Target Server: ${userInput['ServiceURL']}
                  Test Suite: ${userInput['TestSuite']}
                  Test Report: ${env.BUILD_URL}artifact/openapi/reports/index.html
                  For Build details, please go to ${env.BUILD_URL}console""");
    }

}