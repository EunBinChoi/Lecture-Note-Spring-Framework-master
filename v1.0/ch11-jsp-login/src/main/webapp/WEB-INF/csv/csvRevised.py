import pandas as pd

csv = pd.read_csv('./zipdoro.csv', sep=",", encoding='CP949')
address = csv['address']
li = address.to_list()
area1 = []
area2 = []
area3 = []
for i in li:
    i_sp = i.split()
    area1.append(i_sp[0]) 
    area2.append(i_sp[1])
    area3.append(i_sp[2])

csv['area1'] = area1
csv['area2'] = area2
csv['area3'] = area3

csv.to_csv('ziodoro_revised.csv', index=False)