echo 'starting CI server'
echo 'remote dir:'
git config --get remote.origin.url
while [ 1 ]
do
	export changed=0
	echo "checking with github... "
	git pull | grep -q -v 'Already up-to-date.' && changed=1
	if [ $changed -eq 1 ]
	then
		sh buildCIOnce.sh
	else
		echo "no changes"
	fi
	sleep 2m
done