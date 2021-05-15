pipeline {
    agent any
    tools {
       maven 'MyMaven'
       jdk 'MyJava' 
    }
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "localhost:8081"
        NEXUS_REPOSITORY = "maven-nexus-repo"
        NEXUS_CREDENTIAL_ID = "nexus-user-credentials"
    }
      stages {
      	stage('Code Quality Check via SonarQube') {
		   steps {
		       script {
		       def scannerHome = tool 'Sonarqube';
		           withSonarQubeEnv("Sonarqube") {
		           bat "${tool("Sonarqube")}/bin/sonar-scanner \
		           -Dsonar.projectKey=davidof:SimpleApiRest:SimpleApi \
		           -Dsonar.sources=src/main \
		           -Dsonar.java.binaries=target \
		           -Dsonar.host.url=http://localhost:9000 \
		           -Dsonar.login=5f0e23ac47a28e2f25fff275d2a4106dab50d245"
		               }
		           }
		       }
		   }
		stage("Quality Gate") {
           steps {
               timeout(time: 1, unit: 'HOURS') {
                    // Parameter indicates whether to set pipeline to UNSTABLE if Quality Gate fails
                    // true = set pipeline to UNSTABLE, false = don't
                    waitForQualityGate abortPipeline: true
                }
            }
        }   
        stage('Build') {
            steps {
                echo "maven" 
                bat 'mvn install'
            }
        }
        
//         stage('Test') {
//            steps {
//                echo "maven test" 
//                bat 'mvn test'
//            }
//        }
        stage("Publish to Nexus Repository Manager") {
            steps {
                script {
                	echo "Inicio Nexus"
                    pom = readMavenPom file: "pom.xml";
                    echo "Pom leido"
                    echo "${pom.packaging}"
                    filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                    echo "fichero encontrado"
                    echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                    artifactPath = filesByGlob[0].path;
                    artifactExists = fileExists artifactPath;
                    if(artifactExists) {
                        echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";
                        nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: pom.groupId,
                            version: pom.version,
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: artifactPath,
                                type: pom.packaging],
                                [artifactId: pom.artifactId,
                                classifier: '',
                                file: "pom.xml",
                                type: "pom"]
                            ]
                        );
                    } else {
                        error "*** File: ${artifactPath}, could not be found";
                    }
                }
            }
        }
      }
//      post {
//        always {
//            junit '**/target/surefire-reports/*.xml'
//        }  
//    }
}
