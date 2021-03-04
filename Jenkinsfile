pipeline{
    stages{
    stage('SCM checkout'){
        git 'https://github.com/EL-MAHMOUDI/jenkins'
    }
    stage('Compile-Package'){
        sh 'mvn package'
    }
    stage("publish to nexus") {
                steps {
                    script {
                        // Read POM xml file using 'readMavenPom' step , this step 'readMavenPom' is included in: https://plugins.jenkins.io/pipeline-utility-steps
                        pom = readMavenPom file: "pom.xml";
                        // Find built artifact under target folder
                        filesByGlob = findFiles(glob: "target/*.${pom.packaging}");
                        // Print some info from the artifact found
                        echo "${filesByGlob[0].name} ${filesByGlob[0].path} ${filesByGlob[0].directory} ${filesByGlob[0].length} ${filesByGlob[0].lastModified}"
                        // Extract the path from the File found
                        artifactPath = filesByGlob[0].path;
                        // Assign to a boolean response verifying If the artifact name exists
                        artifactExists = fileExists artifactPath;

                        if(artifactExists) {
                            echo "*** File: ${artifactPath}, group: ${pom.groupId}, packaging: ${pom.packaging}, version ${pom.version}";

                            nexusArtifactUploader(
                                nexusVersion: "nexus3",
                                protocol: "http",
                                nexusUrl: "172.17.0.2:8081",
                                groupId: pom.groupId,
                                version: pom.version,
                                repository: "repository-example",
                                credentialsId: "nexus-credentials",
                                artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: pom.artifactId,
                                    classifier: '',
                                    file: artifactPath,
                                    type: pom.packaging],

                                    // Lets upload the pom.xml file for additional information for Transitive dependencies
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
}
