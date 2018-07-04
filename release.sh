#!/bin/sh

getVersionWithoutSnapshot(){
 	grep -oP '(?<=version>)[^<]+' "pom.xml" | sed -n '1p'
}

getVersionWithSnapshot(){
 	grep -oP '(?<=version>)[^<]+' "pom.xml" | grep  'SNAPSHOT' | grep -oP '[^-SNAPSHOT]+' | sed -n '1p'
}

getVersion(){
 	V="$(getVersionWithSnapshot)"
 	if [ -z "$V" ]
	then
		V="$(getVersionWithoutSnapshot)"
	fi
	echo "$V"
} 

nextVersion(){
	VMAJOR=$(echo $2  | cut -d \. -f 1);
	VMINOR=$(echo $2 | cut -d \. -f 2);
	VPATCH=$(echo $2 | cut -d \. -f 3);
	
	case $1 in
	major) 
		VMAJOR=$((VMAJOR+1));
		VMINOR=0;
		VPATCH=0;
	;;
	minor)
		VMINOR=$((VMINOR+1));
		VPATCH=0; 
	;;
	patch) VPATCH=$((VPATCH+1)); ;;
	*) echo "Opcao Invalida!";exit 10 ;;
	esac
	echo "${VMAJOR}.${VMINOR}.${VPATCH}"
}


CURRENT_VERSION=$(getVersion)

if [ -z "$CURRENT_VERSION" ]
then
	echo "Versão inválida"
	exit 10
fi

NEXT_VERSION=$(nextVersion $1 $CURRENT_VERSION)

echo "Alterando de ${CURRENT_VERSION} para ${NEXT_VERSION}"
	
git pull

mvn versions:set -DnewVersion=$NEXT_VERSION;
git add .;
git commit -a -m "Gerada versão $NEXT_VERSION";
git tag -a $NEXT_VERSION -m "Versão $NEXT_VERSION";
git tag -a "release-$NEXT_VERSION" -m "Versão $NEXT_VERSION";


mvn versions:set -DnewVersion="$NEXT_VERSION-SNAPSHOT";
git add .;
git commit -a -m "Iniciando trabalhos na versão $NEXT_VERSION-SNAPSHOT";
	
git push --follow-tags;

