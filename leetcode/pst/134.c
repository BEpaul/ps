int canCompleteCircuit(int* gas, int gasSize, int* cost, int costSize){
    int i, sum = 0;
    for (i = 0; i < gasSize; i++) {
        sum += gas[i] - cost[i];
    }
    if (sum < 0) return -1;

    sum = -1;
    int result;
    
    for (i = 0; i < gasSize; i++) {
        if (sum < 0) {
            sum = 0;
            result = i;
        }

        sum += gas[i] - cost[i];
    }

    return result;
    
}