No se si es importante el readme (le he pasado el proyecto al Chat para que me genere el readme y esto me ha escupido)

# DeliveryBooks

Proyecto Java desarrollado para la actividad 1 de Diseño de Software del ciclo DAW.

## Descripción

DeliveryBooks es la base de dominio de una aplicación destinada a gestionar las necesidades de una empresa de venta de libros a través de Internet.

El proyecto aplica fundamentos de programación orientada a objetos y principios de diseño de software.

## Tecnologías

- Java 21
- Visual Studio Code
- Git y GitHub

## Estructura del proyecto

```text
deliverybooks/
├── src/
│   ├── domain/
│   │   ├── book/
│   │   │   ├── Author.java
│   │   │   ├── Book.java
│   │   │   └── Publisher.java
│   │   ├── client/
│   │   │   └── Client.java
│   │   └── shared/
│   │       ├── validation/
│   │       │   └── Check.java
│   │       └── valueobjects/
│   │           ├── Email.java
│   │           ├── Isbn.java
│   │           ├── Money.java
│   │           ├── Phone.java
│   │           ├── Weight.java
│   │           └── WeightUnit.java
│   ├── services/
│   ├── infrastructure/
│   ├── test/
│   │   └── FunctionalTests.java
│   └── App.java
├── bin/
└── README.md
```

## Dominio

El proyecto contiene dos contextos principales:

- **Client:** gestión de clientes.
- **Book:** gestión de libros, autores y editoriales.

El paquete `shared` contiene las validaciones comunes y los objetos de valor utilizados por el dominio.

## Value Objects

- Email
- Phone
- Isbn
- Money
- Weight
- WeightUnit

Los objetos de valor validan sus datos durante la creación y utilizan factorías estáticas `of()`.

## Validaciones

La clase `Check` centraliza validaciones genéricas para tipos básicos.

Los datos inconsistentes generan excepciones `IllegalArgumentException`.

## Ejecución

Compilar el proyecto desde la raíz:

```powershell
javac -d bin (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

Ejecutar la aplicación:

```powershell
java -cp bin App
```

Ejecutar las pruebas funcionales:

```powershell
java -cp bin test.FunctionalTests
```

## Control de versiones

El proyecto utiliza Git y GitHub para el control de versiones.