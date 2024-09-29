import pickle
import pandas as pd
import numpy as np
import warnings

warnings.filterwarnings("ignore")
with open(r'..\model\decision_tree_model.pkl', 'rb') as model_file:
    dt = pickle.load(model_file)

symptoms = ['skin_rash','itching','nodal_skin_eruptions','increased_appetite','irritability']
data = pd.read_csv(r'..\model\Datasets\Training.csv')
cols = list(data.columns)
ipt = [0 for i in range(len(cols)-1)]
for s in symptoms:
  ipt[cols.index(s)]=1
ipt = np.array([ipt])
# print(ipt)
# print(dt.predict(ipt))
# disease = dt.predict(ipt)
# print(disease[0])

predicted_disease = dt.predict(ipt)[0].strip()  # Strip leading/trailing whitespaces
print(predicted_disease)

