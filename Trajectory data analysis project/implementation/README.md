# Trajectory data anaysis project

**Implementation**
1.Here to algorithm DBSCAN and distance based algorithm implemented on the data set for detecting staypoint
2.We defined stay point in a way if a user stays in a point minimum 600 second for distanced based algorithm
3.We create clusters with minimum 10 points and 0.02 km for dbscn.Then the centre of cluter we defeined as stay point
4.In this way we found staypoints with two different algorithm for public dataset and our collected dataset.THen we compared and evaluated the result 

Initial steps cluster plotting codes at graph has been attcahed for DBSCAN at the folder "  Plot_cluster_initial step".
for a single file has been checked.
The final implementaion and commit process of DBSCAN is present in staypoint_detection_dbscan.py and geolife_staypoint_detection.py file

**Uploading data to the database**
1. Download geolife from this [https://heremaps.github.io/pptk/tutorials/viewer/geolife.html](url)
2. Extract folder "*Data*" into root folder of this project
3. Insert your database credentials into database.ini file.
4. In the "*Data*" folder keep only that user that you want to insert (in our case from 000 till 090)
5. Make sure that in your database there is **geolifeDataset** and **result-groupA** tables. (Structures were defined by Mobi chair)
6. Run **read_geolife.py** file. Finally check the **geolifeDataset** table for inserted trajectories.

**Uploading our own data to the database**
1.We have inserted our own data collected at Bamberg by a python script
2.We have wriiten insert outdoor data,insert outfoor ground truth and other insert python script to insert data at server from our collected csv file

**Staypoint detection**
1. Insert your database credentials into database.ini file.
2. In the **geolife_staypoint_detection.py** specify following variables
   * eps - maximum distance between two coordinates in km for DBSCAN algorithm, in this project eps = 0.01 (10m)
   * minpts - minimum points reqiured to create a cluster for DBSCAN algorithm, in this project minpts = 20
   * dist_thres - distance threshold in meters for Distance-based algorithm, in this project dist_thres = 200 (was defined in the papar from task sheet)
   * time_thres - time threshold in seconds for Distance-based algorithm, in this project time_thres = 1800 (30 minutes, was defined in the papar from task sheet)
   * algorithm - define wich staypoint algorithm to run: *DBSCAN*; *DIST*; *ALL* - both algorithm
3. Make sure that in your database there is **geolifeDataset** and **result-groupA** tables. (Structures were defined by Mobi chair)
4. Run **geolife_staypoint_detection.py** file. Finally check the **result-groupA** table for inserted staypoints.




# ** DATA INSERTION * 

** DATA INSERTION
	*Everyone data as csv file is in the folder GroupAdata_task4_Backup.Outdoor data is different folder according to the name. Indoor  	     data ground truth in single file for all members.
 	* For inserting data Connection with MOBI server should be made .Database name is "Sose2020-mobi-mobility"
	*Three The Data insertion code has been placed in implemntation one for outdoor data two for indoor and outdoor ground truth
	*After opening the code at the csv filelocation the CSV filename was entried in the code code data was inserted
	* user id and trajectory id formation has been done in the python script.

**Parameter Tuning, updates of algorithm**

1. database.ini file with login credentials 
2. Choose which group staypoints are to be logged, and insert that in the dataPart variable.
3. We can also adjust eps, minpts, dist_thres and time_thres, as different users collected data in different ways. We considered eps = 	0.02 km, minpts=10, dist_thres= 70m and time_thres = 600 seconds
4.Choose from *DBSCAN*, *DIST*, *ALL* to run only respective algorithms.
