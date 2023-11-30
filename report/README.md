# Lab report template

## Usage

The report consists of a main file [`report.tex`](report.tex), which includes a cover ([`parts/0-cover.tex`](parts/0-cover.tex)), a Table of Contents (if you uncomment it), and all the Markdown and LaTeX files inside the [`parts/`](parts/) folder that you want to include.


## Compilation

First you must install LaTeX.

- For Linux, install `texlive`.
- For Windows, install [MiKTeX](https://miktex.org/download#win), make sure you add it to your `PATH`, and install [Perl](https://strawberryperl.com/). If it’s not installed already, open the MikTeX Package Manager and install the `latexmk` package.
- For MacOS, install [MacTeX](https://www.tug.org/mactex/mactex-download.html) and then install `latexmk` with:
    ```
    sudo tlmgr install latexmk
    ```

To compile the report, use the command:
```
latexmk -cd -shell-escape -pdf report.tex
```

## Install MongoDB

First you must install Mongo.
- Go to the following [link](https://www.mongodb.com/try/download/community), select the desired package (version and OS) and download it.
- Execute it. Select the Complete version and Run service as Network Service user. Later make sure to select the option to download Compass.
- Copy the route to Mongo server. The default one should be the following depending on the version downloaded: C:\Program Files\MongoDB\Server\6.0\bin
- Go to "Este Equipo> Propiedades> Confifuración avanzada del sistema> variables de entorno" and add a new path by copying the previous one.
- After setting the path, open the terminal and run the following command in order to initialize the server
     ```
     mongod
    ```




