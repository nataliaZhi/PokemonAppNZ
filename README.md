# PokémonNZ App

## Introducción
La **PokémonNZ App** es una aplicación móvil que permite a los usuarios gestionar una lista de Pokémon capturados y explorar una Pokédex completa. Utiliza Firebase para la autenticación y almacenamiento de datos, y consume datos en tiempo real desde la API de Pokémon.

## Características Principales

### **1. Autenticación con Firebase** 
Inicio de sesión con correo electrónico y contraseña predeterminada en Firebase.
Los nuevos usuarios pueden crear una cuenta en la pantalla de registro. Si el usuario ya tiene una cuenta, puede iniciar sesión directamente. Si la sesión está cerrada, el usuario será redirigido automáticamente a la pantalla de inicio de sesión.
  
**Captura de pantalla**: ![Pantalla de Login](ruta/a/la/imagen.png) 

### **2. Pokedex** 
Navega por una lista obtenida de la API de Pokémon mediante Retrofit.
Añade Pokémon a tu lista personal con un solo toque, y sincroniza los cambios en tiempo real con Firebase.
 
**Captura de pantalla**: ![Pantalla de Pokedex](ruta/a/la/imagen.png) 

### **3. Mis Pokémon** 
Visualiza los Pokémon capturados, se puede habilitar eliminación de Pokémon desde los ajustes. Al pulsar un Pokémon, se muestran los detalles del Pokémon. 

**Captura de pantalla**: ![Pantalla de Pokemon Capturados](ruta/a/la/imagen.png) 

### **4. Detalles del Pokémon** 
**Captura de pantalla**: ![Pantalla de Detalles](ruta/a/la/imagen.png) 

### **5. Ajustes** 
  - Cambio de idioma entre inglés y castellano.
  - Activar o desactivar la eliminación de Pokémon capturados.
  - Cerrar sesión en Firebase.

**Captura de pantalla**: ![Pantalla de Ajustes](ruta/a/la/imagen.png) 

## Tecnologías Utilizadas
- **Lenguaje**: Java.
- **IDE**: Android Studio.
- **Librerías y herramientas**:
  - **Retrofit**: Consumo de la API de Pokémon.
  - **Firebase Authentication y Firestore**: Para autenticación y almacenamiento de datos.
  - **Navigation Component**: Manejo eficiente de navegación entre fragmentos.
  - **ViewModel y LiveData**: Gestión reactiva de datos.
  - **RecyclerView**: Visualización de listas.
  - **Picasso**: Carga optimizada de imágenes.
  - **SharedPreferences**: Configuración de preferencias locales.

## Instrucciones de Uso

### Pasos para Ejecutar

**Clonar el Repositorio:**
```sh
git clone https://github.com/nataliaZhi/PokemonAppNZ
```


**Abrir el Proyecto en Android Studio:**
  - Abre Android Studio.
  - Selecciona "Open an Existing Project".
  - Navega hasta la carpeta donde clonaste el repositorio y selecciona el proyecto.
    
**Configurar Firebase:**
- Crea un proyecto en la consola de Firebase.
- Añade una aplicación Android a tu proyecto de Firebase.
- Descarga el archivo `google-services.json` y colócalo en la carpeta `app` de tu proyecto.
- Habilita el método de autenticación de Google en Firebase Authentication.
- Habilita Firestore en Firebase.

**Instalar Dependencias:**
Android Studio debería detectar automáticamente las dependencias necesarias. Si no es así, sincroniza el proyecto con los archivos Gradle (File > Sync Project with Gradle Files).

**Ejecutar la Aplicación:**
  - Conecta un dispositivo Android o inicia un emulador.
  - Haz clic en el botón "Run" (el icono de play verde) en Android Studio.



## Conclusiones del Desarrollador





