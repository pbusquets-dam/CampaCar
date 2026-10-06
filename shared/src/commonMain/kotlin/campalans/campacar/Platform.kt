package campalans.campacar

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform