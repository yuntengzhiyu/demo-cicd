pipeline {
    agent any

    stages {
        stage('拉取代码') {
            steps {
                echo '正在拉取代码...'
                checkout scm
            }
        }

        stage('构建镜像') {
            steps {
                echo '正在构建 Docker 镜像...'
                sh 'docker build -t demo-cicd:latest .'
            }
        }

        stage('部署') {
            steps {
                echo '正在部署...'
                sh '''
                    docker stop demo-app || true
                    docker rm demo-app || true
                    docker run -d --name demo-app -p 8081:8081 demo-cicd:latest
                '''
            }
        }
    }

    post {
        success {
            echo '部署成功！'
        }
        failure {
            echo '构建失败！'
        }
    }
}
