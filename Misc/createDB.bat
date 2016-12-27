@echo off

mkdir c:\Kuiper\MongoDB
mkdir c:\Kuiper\MongoDB\db_30000
mkdir c:\Kuiper\MongoDB\db_30000\log
mkdir c:\Kuiper\MongoDB\db_30001
mkdir c:\Kuiper\MongoDB\db_30001\log
mkdir c:\Kuiper\MongoDB\db_29999
mkdir c:\Kuiper\MongoDB\db_29999\log
mkdir c:\Kuiper\MongoDB\etc


set primaryCfg=c:\Kuiper\MongoDB\etc\KuiperPrimarydb.cfg
set secondaryCfg=c:\Kuiper\MongoDB\etc\KuiperSecondarydb.cfg
set arbiteryCfg=c:\Kuiper\MongoDB\etc\KuiperArbiterydb.cfg

set primaryDB=c:/Kuiper/MongoDB/db_30000
set secondaryDB=c:/Kuiper/MongoDB/db_30001
set arbiteryDB=c:/Kuiper/MongoDB/db_29999

set primaryLog=c:/Kuiper/MongoDB/db_30000/log/primaryKuiper.log
set secondaryLog=c:/Kuiper/MongoDB/db_30001/log/secondaryKuiper.log
set arbiteryLog=c:/Kuiper/MongoDB/db_29999/log/arbiteryKuiper.log

set primaryPort=30000
set secondaryPort=30001
set arbiterPort=29999



@echo # data place holder > %primaryCfg%
@echo dbpath=%primaryDB%  >> %primaryCfg%

@echo #log >> %primaryCfg%
@echo logpath=%primaryLog% >> %primaryCfg%

@echo #Debug level >> %primaryCfg%
@echo verbose=v >> %primaryCfg%

@echo #port >> %primaryCfg%
@echo port=%primaryPort% >> %primaryCfg%

@echo #replSet >> %primaryCfg%
@echo replSet=Kuiper >> %primaryCfg%


@echo # data place holder > %secondaryCfg%
@echo dbpath=%secondaryDB%  >> %secondaryCfg%

@echo #log >> %secondaryCfg%
@echo logpath=%secondaryLog% >> %secondaryCfg%

@echo #Debug level >> %secondaryCfg%
@echo verbose=v >> %secondaryCfg%

@echo #port >> %secondaryCfg%
@echo port=%secondaryPort% >> %secondaryCfg%

@echo #replSet >> %secondaryCfg%
@echo replSet=Kuiper >> %secondaryCfg%



@echo # data place holder > %arbiteryCfg%
@echo dbpath=%arbiteryDB%  >> %arbiteryCfg%

@echo #log >> %arbiteryCfg%
@echo logpath=%arbiteryLog% >> %arbiteryCfg%

@echo #Debug level >> %arbiteryCfg%
@echo verbose=v >> %arbiteryCfg%

@echo #port >> %arbiteryCfg%
@echo port=%arbiterPort% >> %arbiteryCfg%

@echo #replSet >> %arbiteryCfg%
@echo replSet=Kuiper >> %arbiteryCfg%



mongod -f %primaryCfg% --install --serviceName primaryKuiper --serviceDisplayName primaryKuiper
mongod -f %secondaryCfg% --install --serviceName secondaryKuiper --serviceDisplayName secondaryKuiper
mongod -f %arbiteryCfg% --install --serviceName arbiteryKuiper --serviceDisplayName arbiteryKuiper


net start primaryKuiper
net start secondaryKuiper
net start arbiteryKuiper

mongo localhost:%primaryPort%/Kuiper_db c:/Kuiper/Misc/replsetup.js
