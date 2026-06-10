package com.rajk2007.kino.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle

class RepoInstallerActivity : Activity() {

    companion object {
        private val REPO_URLS = listOf(
            "https://raw.githubusercontent.com/self-similarity/MegaRepo/builds/repo.json",
            "https://raw.githubusercontent.com/recloudstream/extensions/master/repo.json",
            "https://raw.githubusercontent.com/phisher98/cloudstream-extensions-phisher/refs/heads/builds/repo.json",
            "https://raw.githubusercontent.com/SaurabhKaperwan/CSX/builds/CS.json"
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            val prefs = getSharedPreferences("kino_prefs", MODE_PRIVATE)
            if (!prefs.getBoolean("repos_installed", false)) {
                prefs.edit()
                    .putStringSet("pending_repos", REPO_URLS.toSet())
                    .putBoolean("repos_installed", true)
                    .apply()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            launchMainActivity()
        }
    }

    private fun launchMainActivity() {
        try {
            val mainActivityClass = Class.forName("com.lagradost.cloudstream3.MainActivity")
            val intent = Intent(this, mainActivityClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            finish()
        }
    }
}
