node{
    stage('SCM checkout'){
        git 'https://github.com/EL-MAHMOUDI/jenkins'
    }
    stage('Compile-Package'){
        sh 'mvn package'
    }
}
