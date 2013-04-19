ant ci-setup runTest > antlog.txt
mv antlog.txt reports/antlog.txt
ant archive-status archive
echo "Build Over"