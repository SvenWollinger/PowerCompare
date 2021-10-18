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

License
---
MIT License

Copyright (c) 2021 SvenWollinger

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
