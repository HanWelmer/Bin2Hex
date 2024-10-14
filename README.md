# Bin2Hex
Java command line utility that transforms a binary file into Intel Hex format.

## Development:
The project was developed using:
* openjdk 11.0.16.1
* Apache Maven 3.9.4
* eclipse 4.26.0

### maven
* open commandline tool
* nagivate to subfolder 
* mvn compile
* mvn test

## Usage:
`java -jar Bin2Hex.jar filename.ext`

`where filename.ext is file to be converted to Intel Hex.`

## Known limitations:
The following limtations are in place, just because in its current state the utility suits mu purposes. It requires no rocket science to remove these limitations. 
* File enstension of input must be .bin.
* Output file has same name as input file and extension .hex.
* Only record length of 16 bytes is supported (not 32 bytes).
* Start address is assumed to be 0x000.
