1. 阿里云支持的插件
https://help.aliyun.com/document_detail/35431.html?spm=a2c4g.11186623.6.630.71626867mCunJc

2. Pl/java
https://help.aliyun.com/document_detail/50594.html?spm=a2c4g.11186623.6.638.764f7207DyNa2Q

3. 插件列表
https://www.alibabacloud.com/help/zh/doc-detail/142340.htm
   
3. 图式搜索
https://developer.aliyun.com/article/328141
   
3. 存储过程对事务的支持
https://www.postgresql.org/docs/13/plpgsql-transactions.html
   
3. 分库分表方案shardingsphere(大数据量解决方案)
https://shardingsphere.apache.org/document/current/cn/overview/
   
3. jsonschema To DB结构
https://github.com/better/jsonschema2db/blob/master/jsonschema2db.py
   
1. 中文文档
   http://www.postgres.cn/docs/12/index.html

## 插件清单

### 监控：
pg_stat_statements
system_stats用于观察OS层的信息
pg_stat_kcache可以观察单条SQL花费多少cpu等
pg_stat_monitor：pg_stat_statements和pg_stat_kcache的结合体
pgpro_stats：等待事件统计、采样配置、自动化监控
pgsentinel、pgsampler：类Oracle ASH

### 命令行：
pg_top，类top工具
pgcenter：全能监控工具
pg_activity：命令行top工具
pg_sysstat：相对简陋

### SQL：
pg_hint_plan：hint功能
pg_query：高亮，识别风险SQL
pg_plan_advsr：懒人优化
hypopg：类似MySQL虚拟索引
sr_plan：保存、篡改、固定 执行计划，Oracle兼容
pg_parallizator：并行创建索引

### JOB：
pg_timetable
pg_cron
pg_agent

### 语言扩展：（存储过程）
plv8
pl/plpythonu
pl/java

### 连接池：
pgbouncer
pgagroal
odyssey

### 巡检：
Toolkit——percona

### sharding：
citus、pg_shardman、plproxy

### FDW：
oracle_fdw
mysql_fdw
mongo_fdw
sqlserver_fdw
file_fdw
等等

### Other：
ADG：图数据库
Cayley: 图数据库
AgensGraph：图数据库
pg_buffercache：观察buffer
pgstattuple：行级别的统计
pg_filedump、pg_waldump、pg_walminer、pg_fix：观察文件、日志，修改日志
pgtrashcan：垃圾回收站
pg_timeout：空闲会话超时，pg14引入idle_session_timeout参数
pgcrypto：数据加密模块
pg_audit、pg_log_userqueries、pgreplay：审计
pageinspect：内窥数据库BLOCK的内容
passwordcheck：密码复杂度检测
pg_buffercache：统计数据库shared buffer的内容
pgcrypto：加密插件
pg_freespacemap：观察数据库fsm文件内容
pgrowlocks：行锁统计
pgstattuple,：记录级别统计信息观察
pg_trgm：模糊查询, 相似文本查询
pg_visibility：观察数据库block的vm标签值(all visibility, frozen, dirty等)
pg_prewarm、pg_fincore：数据预热
tablefunc：行列转换，connect by
auto_explain、pg_show_plans：执行计划
zhparser、pg_jieba：中文分词
pg_trgm、pg_bigm(没有3个分词限制)、pgroonga：模糊查询
pg_similarity、cube、rum：相似查询
pg_pathman、pg_partman：分区
pg_qualstats：索引建议
pg_wait_sampling：等待事件采样
citext：大小写
pg_query_state：后台工作情况
session_exec：失败超过次数自动锁定
postgis：强大的地理空间数据
pg_readonly：设为只读，类似transaction_read_only
pg_tt：全局临时表
pg_dropbuffer、pg_dropcache：删除cache和buffer
set_user、pg_permissions、pg_restrict：ACL，权限进一步加强
diskquota：类Oracle profile，不过只能限制磁盘
pg_prioritize：进程优先级调度
sql_firewall：SQL防火墙
auth_delay：防破解、安全
timescaledb：时序数据库
md5hash、gzip（wget ）、pgzstd：加密压缩
ddlx、pgddl：获取DDL
uuid-ossp：uuid生成
pipelindb：流式计算
orafce：oracle兼容、package一些内置函数等
pg_roaringbitmap：精准营销
pg_repack、pg_sequeeze：冻结、重建、垃圾回收
AWR：pg_awr、pg_profile
逻辑复制、cdc相关：wal2json、wal2mongo、decoder_raw、pglogical、decoderbufs
zedstore, 行列混合存储
undam、zheap：undo引擎
pgpool：读写分离、负载均衡
