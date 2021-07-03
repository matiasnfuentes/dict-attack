Modo de uso:

1. Abrir la consola
2. Situarse en el directorio que contiene a "dictattack.jar"
3. Ejecutar el comando "java -jar dictattack.jar [TamañodeBuffer] [CantidadDeThreads] [maxSalt]
donde se debe reemplazar:

	[TamañodeBuffer]  -> Por el tamaño del buffer que queremos utilizar
	[CantidadDeThreads] -> Por la cantidad de threads que queremos utilizar
	[maxSalt] -> El salt que queremos utilizar
4. Al finalizar la ejecución los password crackeados estarán en el archivo".\src\main\resources\cracked.txt"