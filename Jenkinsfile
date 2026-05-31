pipeline {
    agent any

    environment {
        HARBOR_URL    = '10.174.130.158'
        HARBOR_PROJECT = 'demo'
        IMAGE_NAME    = 'demo-cicd'
        IMAGE_TAG     = "${BUILD_NUMBER}"
        FULL_IMAGE    = "${HARBOR_URL}/${HARBOR_PROJECT}/${IMAGE_NAME}:${IMAGE_TAG}"
    }

    stages {
        stage('拉取代码') {
            steps {
                checkout scm
            }
        }

        stage('构建 jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('构建镜像') {
            steps {
                sh "docker build -t ${FULL_IMAGE} ."
            }
        }

        stage('推送镜像到 Harbor') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'harbor-credentials',
                    usernameVariable: 'HARBOR_USER',
                    passwordVariable: 'HARBOR_PASS'
                )]) {
                    sh """
                        docker login ${HARBOR_URL} -u ${HARBOR_USER} -p ${HARBOR_PASS}
                        docker push ${FULL_IMAGE}
                        docker logout ${HARBOR_URL}
                    """
                }
            }
        }

        stage('部署到 K8s') {
            steps {
                sh """
                    sed -i 's|IMAGE_PLACEHOLDER|${FULL_IMAGE}|g' k8s/deployment.yaml
                    kubectl apply -f k8s/deployment.yaml
                """
            }
        }
    }

    post {
        success { echo '部署成功！' }
        failure { echo '构建失败！' }
    }
}
