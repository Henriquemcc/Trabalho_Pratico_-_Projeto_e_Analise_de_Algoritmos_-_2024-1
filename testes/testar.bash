#!/bin/bash

# Arquivos
arquivos=(5_franquias_2_lojas.txt exemplo_professor.txt)

# Distâncias mínimas
distancias_minimas=(0 1 2 4 8 16 32 64 128 256 512 1024)

# Algoritmos
algoritmos=(forca-bruta branch-and-bound)

# Criando pasta para armazenar os resultados
mkdir -p "resultados"

# Executando o programa
for (( i = 0; i < 3; i++ )); do
  for arquivo in "${arquivos[@]}" ; do
      for distancia_minima in "${distancias_minimas[@]}" ; do
          for algoritmo in "${algoritmos[@]}" ; do
              echo "$arquivo $distancia_minima $algoritmo"
              java -jar app.jar --calcular-tempo --arquivo-dados "$arquivo" --distancia-minima "$distancia_minima" --algoritmo "$algoritmo" > "resultados/$arquivo-$distancia_minima-$algoritmo.$(date "+%d-%m-%Y_%H:%M:%S").txt"
          done
      done
  done
done
