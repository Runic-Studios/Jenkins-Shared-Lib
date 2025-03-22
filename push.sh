docker build -f jenkins-agent.Dockerfile -t registry.runicrealms.com/jenkins/jenkins-agent:latest .
docker push registry.runicrealms.com/jenkins/jenkins-agent:latest
