# Acceso-a-Datos
La primera pantalla será la pantalla de login. En esta pantalla se visualizará un
formulario con el que se le pedirá al usuario su usuario y contraseña de MySQL
Workbench y mediante esos datos se hará login en el servidor de MySQL. Es
decir, al pulsar el botón de inicio de sesión en este formulario se tomará el
usuario y contraseña y se almacenará el objeto Connection para su uso en
pantallas posteriores, de forma que sólo se hará login una única vez durante el
ciclo de vida de la aplicación. Si se consigue hacer login con éxito, se pasará al
siguiente formulario. Si no, se mostrará un mensaje de error en el formulario y
seguiremos en el mismo hasta que se introduzca un usuario y contraseña válidos.
2. En la segunda ventana usar un combobox que se rellene mediante JDBC con los
nombres de las distintas tablas, y que el usuario seleccione la tabla de entre esas
opciones tablas de Northwind. Al seleccionar una tabla y darle a siguiente pasará
a la siguiente pantalla.
3. La última pantalla será la que muestre el contenido de la tabla cuyo nombre se
seleccionó en la ventana anterior. Debe haber un botón que permita volver hacia
la ventana de selección de tabla. El contenido de la tabla se mostrará con un
control del tipo adecuado, de forma que se muestren todas las filas de la tabla y
todas las columnas de cada fila.
