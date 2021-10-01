# PowerCompare
Compact Compare tool

A tool to compare process output.

## How to use
Either by command line:
```java -jar PowerCompare.jar C:\file1.txt C:\file2.txt```

Or by using the guy that starts automaticly when not launched like above. Simply drop both files in there after eachother

## Example

Example input:
```
Status   Name               DisplayName                           
------   ----               -----------                           
Stopped  edgeupdate          Microsoft Edge-Update-Dienst (edge...
Stopped  edgeupdatem         Microsoft Edge-Update-Dienst (edge...
Stopped  AxInstSV           ActiveX-Installer (AxInstSV)          
Stopped  AJRouter           AllJoyn-Routerdienst                  
Stopped  wlidsvc            Anmelde-Assistent für Microsoft-Konten
Running  Netlogon           Anmeldedienst                         
Running  VaultSvc           Anmeldeinformationsverwaltung         
Running  UmRdpService       Anschlussumleitung für Remotedeskto...
Stopped  AppIDSvc           Anwendungsidentität                   
```

Example output:
```
Changed items:
Running:
Running: ESET Firewall Helper                   ekrnEpfw           
Running: Dienst für Bildschirmtastatur und S... TabletInputService 
Running: Update Orchestrator Service für Win... UsoSvc                   

Stopped:
Stopped: Xbox Live Authentifizierungs-Manager   XblAuthManager     
Stopped: COM+-Systemanwendung                   COMSysApp          
Stopped: SNMP-Trap                              SNMPTRAP           

Removed:
Removed: Benutzerdatenspeicher _43ae12          UnistoreSvc_43ae12 
Removed: CDPUserSvc_43ae12                      CDPUserSvc_43ae12  

Added:
Stopped: Benutzerdatenzugriff_85d5a428          UserDataSvc_85d... 
```
