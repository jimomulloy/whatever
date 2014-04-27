 #!/bin/bash         

echo "Start"
echo $1
git fetch origin
git rebase origin/$1
git rebase origin/master
git push origin $1
git checkout master  #(switch branch locally)
git pull origin master
git merge --no-ff $1  #(merge ch05 inot master)
echo "Done"