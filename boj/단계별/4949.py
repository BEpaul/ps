while(True):
    input_value = input()
    li = []
    flag = True

    if input_value == '.':
            break

    else:
        for val in input_value:
            if val == '[':
                li.append(val)
                continue

            elif val == ']':
                if li:
                    if li[len(li)-1] == '[':
                        li.pop()
                        continue

                    else:
                        flag = False
                        break
                
                else:
                    flag = False
                    break

            elif val == '(':
                li.append(val)
                continue

            elif val == ')':
                if li:
                    if li[len(li)-1] == '(':
                        li.pop()
                        continue

                    else:
                        flag = False
                        break
                else:
                    flag = False
                    break

            else:
                continue

        if not li and flag == True:
            print('yes')
            continue
        
        else:
            print('no')
            continue
