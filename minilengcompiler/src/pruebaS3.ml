%------------------------------------------------------
programa pruebas3;
%------------------------------------------------------

    entero n, i;

    %------------------------------------------------------
    accion es_primo(val entero p1; ref booleano p2; ref caracter p3);
    %------------------------------------------------------
    entero j;
    booleano divisible;

    principio
      j := ; % sigue ejecutandose
      a := ; % sigue ejecutandose
      divisible := false;
      mq (j + (i div 2)) and not divisible
	     divisible := i mod j == 0; % dos iguales no funciona
	     j := j + 1;
      fmq
      si not divisible ent
	     escribir (i, "es primo.", entacar(13), entacar(10));
      fsi
    fin

%------------------------------------------------------
principio
%------------------------------------------------------
  n := 100;
  i := 2;
  mq i <= n
	 es_primo;
	 i := i + 1;
  fmq
fin

