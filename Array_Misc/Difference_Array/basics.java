Query = [l , r , v , 1]
  
Diff_Array Addidition

  diff[l] += v;

  diff[r+1] -= v;



Diff_Array Multiplication

  diff[l] *= v;

  diff[r+1] /= v;


for(int i=1;i < n; i++){
  diff[i] += diff[i-1]; 
}

---------------------------------------------------

Query = [l , r , v , k]

  Diff_Array_With_Jumps
  
  perform query in range of l to r ,
  query = add v 
  after each k steps

diff[l] += v;

steps = (r-l)/k;

next = l + (steps + 1) * k;

diff[next] -= v;


for(int i=0;i < n; i=i+k){
  if(i-k>=0)
  diff[i] += diff[i-k]; 
}

-----------------------



  
