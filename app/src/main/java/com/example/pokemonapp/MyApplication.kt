package com.example.pokemonapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class annotated with `@HiltAndroidApp` to enable Hilt for dependency injection.
 * This annotation generates the necessary components and modules for Hilt to function properly.
 */
@HiltAndroidApp
class MyApplication : Application()

