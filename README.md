# 💱 Conversor de Monedas - Challenge ONE Java

Bienvenido a mi solución del **Challenge de Programación: Conversor de Monedas**, parte de la especialización Backend del programa **Oracle Next Education (ONE)** en alianza con **Alura**.

## 📖 Descripción del Proyecto
Este software es una herramienta robusta de línea de comandos capaz de realizar conversiones de divisas en tiempo real. Utiliza la API de **ExchangeRate** para garantizar que los cálculos se basen en las tasas de mercado más actuales. 

El proyecto implementa una arquitectura limpia y una experiencia de usuario (UX) fluida para el entorno de terminal, separando la lógica de negocio de la interacción con el usuario.

## 🌟 Funcionalidades Principales
* **Consulta de Tasas en Tiempo Real:** Conexión directa a la API para obtener datos actualizados.
* **Filtro Inteligente:** Solo se procesan y almacenan las monedas definidas como relevantes en el sistema.
* **Historial de Consultas:** Registro automático de cada conversión, incluyendo fecha, hora, valores de origen y destino.
* **Interfaz Dinámica:** Menús interactivos que guían al usuario paso a paso.
* **Manejo de Errores Avanzado:** Validación de entradas para evitar cierres inesperados por caracteres inválidos.

## 🛠️ Tecnologías y Herramientas
* **Java SDK 20:** Uso de características modernas como *Records* y *Text Blocks*.
* **GSON 2.10.1:** Biblioteca para el mapeo eficiente de JSON a objetos Java.
* **Java HttpClient:** Gestión de solicitudes HTTP modernas.
* **IntelliJ IDEA:** Entorno de desarrollo para la creación y depuración.

## ⚙️ Arquitectura del Software
El proyecto sigue el principio de **Responsabilidad Única (SRP)**:
* **`Main`**: Controlador de la interacción y flujo del menú.
* **`ServicioMoneda`**: Núcleo lógico que consume la API y realiza los cálculos.
* **`Moneda (Enum)`**: Centralización de las divisas soportadas (ARS, BRL, CLP, COP, USD, EUR, BOB).
* **`Consulta`**: Entidad encargada de estructurar los datos para el historial.
* **`RespuestaApi`**: Record optimizado para recibir datos JSON.

## 🚀 Cómo Empezar

### Requisitos Previos
* Java JDK 11 o superior instalado.
* Una API Key de **ExchangeRate-API**.

### Instalación
1. **Configura tu API Key** como variable de entorno:
```bash
# En Windows
setx API_KEY_EXCHANGE "tu_clave_aqui"

# En Linux/Mac
export API_KEY_EXCHANGE="tu_clave_aqui"
```
Clona este repositorio:
Bash
```text
git clone [https://github.com/yerkoppp/conversor_monedas_alura.git](https://github.com/yerkoppp/conversor_monedas_alura.git)Compila y ejecuta el archivo Main.java.
```
### 📊 Ejemplo de Uso
```text
**********************************************
-------------------- Menú --------------------

   (1) Convertir moneda
   (2) Ver historial de consultas
   (0) Salir

Seleccione una opción: 1
----------------------------------------------
Seleccione la moneda de ORIGEN (o 0 para cancelar):
1 (USD)
----------------------------------------------
Seleccione la moneda de DESTINO (o 0 para cancelar):
4 (CLP)
----------------------------------------------
Ingrese la cantidad a calcular: 100

+----------------------------------------------+
|             RESULTADO DE CONVERSIÓN          |
+----------------------------------------------+
  Origen:  US$ 100,00 (Dólar Estadounidense)
  Destino: CL$ 94500,00 (Peso Chileno)
+----------------------------------------------+
```
## 👨‍💻 Autor

Yerko Osorio 