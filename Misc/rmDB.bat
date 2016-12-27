@echo off

net stop primaryKuiper
net stop secondaryKuiper
net stop arbiteryKuiper

mongod --remove 
 
del /q /s C:\Kuiper\MongoDB 

