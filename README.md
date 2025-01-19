# PokémonNZ App

## Introducción
La **PokémonNZ App** es una aplicación móvil que permite a los usuarios gestionar una lista de Pokémon capturados y explorar una Pokédex completa. Utiliza Firebase para la autenticación y almacenamiento de datos, y consume datos en tiempo real desde la API de Pokémon.

## Características Principales

### **1. Autenticación con Firebase** 
Inicio de sesión con correo electrónico y contraseña predeterminada en Firebase.
Los nuevos usuarios pueden crear una cuenta en la pantalla de registro. Si el usuario ya tiene una cuenta, puede iniciar sesión directamente. Si la sesión está cerrada, el usuario será redirigido automáticamente a la pantalla de inicio de sesión.

 ![Pantalla de Login](https://github.com/nataliaZhi/PokemonAppNZ/blob/master/login.PNG)

### **2. Pokedex** 
Navega por una lista obtenida de la API de Pokémon mediante Retrofit.
Añade Pokémon a tu lista personal con un solo toque, y sincroniza los cambios en tiempo real con Firebase.
 
  ![Pantalla de Pokedex](https://github.com/nataliaZhi/PokemonAppNZ/blob/master/Pokedex.PNG) 

### **3. Mis Pokémon** 
Visualiza los Pokémon capturados, se puede habilitar eliminación de Pokémon desde los ajustes. Al pulsar un Pokémon, se muestran los detalles del Pokémon. 

 ![Pantalla de Pokemon Capturados](https://github.com/nataliaZhi/PokemonAppNZ/blob/master/MisPokemon.PNG)

### **4. Detalles del Pokémon** 
En esta pantalla se muestran los siguientes detalles de Pokémon: Foto, Nombre, Índice de la Pokédex, Tipo(s), Peso y altura.

 ![Pantalla de Detalles](https://github.com/nataliaZhi/PokemonAppNZ/blob/master/detalles.PNG)

### **5. Ajustes** 
  - Cambio de idioma entre inglés y español.
  - Activar o desactivar la eliminación de Pokémon capturados.
  - Acerca de... que muestre el nombre del desarrollador y la versión actual de la aplicación.
  - Cerrar sesión en Firebase.

![Pantalla de Ajustes](https://github.com/nataliaZhi/PokemonAppNZ/blob/master/Ajustes.PNG)

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
###  Creación de la Interfaz
La creación de la interfaz ha sido relativamente sencilla. Implementó un MainActivityque actúa como el contenedor principal y creó Fragmentspara cada una de las pestañas: Pokémon Capturados, Pokédex y Ajustes. He utilizado BottomNavigationView para gestionar la navegación entre las pestañas. Esto permite una navegación fluida y una organización clara de las vistas. La implementación de los RecyclerView en los fragmentos de Pokémon Capturados y Pokédex también fue bastante directa, y me permitió mostrar listas de Pokémon de manera efectiva.


###  Autenticación con Firebase
Para la autenticación con Firebase, siga la guía proporcionada por la profesora, lo cual facilitó mucho el proceso. Configuré Firebase Authentication para gestionar el inicio de sesión de los usuarios y añadí autenticación mediante Google para permitir un registro y acceso rápido y seguro con las cuentas de Google de los usuarios. Aunque la configuración inicial fue sencilla, tuve que prestar especial atención a los detalles de configuración en la consola de Firebase y asegurarme de que todas las dependencias necesarias estuvieran correctamente agregadas en el proyecto.

### Funcionalidad de la pantalla Ajustes
La implementación de la pantalla de Ajustes presentó algunos desafíos, especialmente en lo que respeta a la funcionalidad del botón de eliminar y su habilitación y deshabilitación desde la misma pantalla de ajustes. Para lograr esto, utilicé SharedPreferences para almacenar las preferencias del usuario y asegurarme de que los cambios se reflejarán en tiempo real. Implementar el Switch para permitir la eliminación de Pokémon capturados requería actualizar el adaptador del RecyclerView en Pokémon Capturados según la preferencia del usuario, lo cual fue un reto interesante.

### Petición GET a la API de Pokémon para llenar la Pokédex

Para realizar la petición GET a la API de Pokémon y llenar la Pokédex, creé varias clases para manejar la lógica de red y modelado de datos. Utilicé la librería Retrofit para realizar las peticiones HTTP, lo cual simplificó mucho el proceso de conexión con la API. Implementé una clase PokemonService para definir los endpoints de la API y una clase PokemonRepository para gestionar las operaciones de red y la persistencia de datos. La parte más desafiante fue manejar la paginación y asegurar que la interfaz de usuario se actualice correctamente con los datos obtenidos de la API en tiempo real.

### Pokémon capturados
Lo más complicado era guardar y recuperar los Pokémon de Firebase. Para esto he utilizado Firebase Firestore, que me permitió almacenar los datos de los Pokémon capturados en una colección y recuperarlos cuando fuera necesario. Implementar la lógica para agregar Pokémon a la colección y eliminarlos si fuera necesario presentó algunos desafíos, especialmente en lo que respeta a la sincronización de datos entre Firestore y la interfaz de usuario. Sin embargo, una vez configurado correctamente, Firestore proporcionó una solución robusta para la gestión de datos en tiempo real.

### Creación y Subida de un Archivo README.md
Desde GitHub, añadí el archivo nuevo y fui escribiendo el texto mientras miraba cómo quedaba. Esto me permitió documentar el proyecto de manera efectiva, proporcionando una descripción clara y concisa de su propósito, características y cómo configurarlo. La interfaz de GitHub para la edición de archivos facilitó mucho este proceso, permitiéndome hacer cambios y ver el resultado en tiempo real.

### **Conclusión**
En general, el desarrollo de esta aplicación fue una experiencia enriquecedora. La creación de la interfaz fue bastante directa, aunque requirió a los detalles para asegurar una navegación fluida y una presentación clara de los datos. La autenticación con Firebase, aunque inicialmente sencilla de configurar, requirió una atención cuidadosa a los detalles de seguridad y configuración. La pantalla de ajustes presenta desafíos interesantes, especialmente en la implementación de la eliminación de Pokémon y la persistencia de preferencias del usuario. Finalmente, la integración con la API de Pokémon para llenar la Pokédex fue una tarea compleja pero gratificante, que me permitió aprender y aplicar nuevas técnicas de desarrollo de aplicaciones móviles.
