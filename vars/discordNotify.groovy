def startBuild(String project, String gitUrl, String gitBranch, String gitCommit, String webhookUrl) {
    discordSend webhookURL: webhookUrl,
            title: "Build Started: ${project} 🚀",
            description: "Jenkins for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "SUCCESS"
}

def successBuild(String project, String gitUrl, String gitBranch, String gitCommit, String webhookUrl) {
    discordSend webhookURL: webhookUrl,
            title: "Build Success: ${project} ✅",
            description: "Jenkins passed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "SUCCESS"
}

def failBuild(String project, String gitUrl, String gitBranch, String gitCommit, String webhookUrl) {
    discordSend webhookURL: webhookUrl,
            title: "Build Failed: ${project} ❌",
            description: "Jenkins failed for [${project}](${gitUrl}) on ref **${gitBranch}** for commit ${gitCommit}",
            footer: "Runic Realms Jenkins",
            result: "FAILURE"
}