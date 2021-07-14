#一次性修改版本号

mvn versions:set -DnewVersion=1.0.1-SNAPSHOT
mvn versions:update-parent
mvn versions:update-child-modules
mvn versions:commit