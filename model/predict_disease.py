import pickle
import pandas as pd
import numpy as np

with open('decision_tree_model.pkl', 'rb') as model_file:
    dt = pickle.load(model_file)

symptoms = ['skin_rash','itching','nodal_skin_eruptions','increased_appetite','irritability']
data = pd.read_csv(r'C:\Users\SANKET\Documents\Projects\Diagnose-AI\model\Datasets\Training.csv')
cols = list(data.columns)
ipt = [0 for i in range(len(cols)-1)]
for s in symptoms:
  ipt[cols.index(s)]=1
ipt = np.array([ipt])
print(ipt)
# print(dt.predict(ipt))
disease = dt.predict(ipt)
print(disease[0])

