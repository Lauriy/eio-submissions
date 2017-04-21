from math import ceil


def calculate_forces(fulcrum_point):
    left = 0
    right = 0
    for item in data:
        if item[0] < fulcrum_point:
            left += round((fulcrum_point - item[0]) * item[1], 4)
        elif item[0] > fulcrum_point:
            right += round((item[0] - fulcrum_point) * item[1], 4)

    return left, right


def recurse(fulcrum_point, step, last_difference):
    forces = calculate_forces(fulcrum_point + step)
    difference = abs(forces[0] - forces[1])
    print('Forces for %s are %s, the difference is %s, last difference was %s' % (
        fulcrum_point + step, forces, difference, last_difference))
    if difference == 0:
        with open('kangval.txt', 'w') as out:
            out.write(str(fulcrum_point + step))
    elif difference > last_difference:
        recurse(fulcrum_point, step / 10, difference)
    else:
        recurse(fulcrum_point, step + step / 10, difference)


raw_data = open('kangsis.txt').readlines()
data = []
lever_length = 0
number_of_weights = 0
for (key, value) in enumerate(raw_data):
    if key == 0:
        meta = value.split(' ')
        lever_length = int(meta[0])
        number_of_weights = int(meta[1])
    else:
        line_data = value.split(' ')
        data.append((int(line_data[0]), int(line_data[1].rstrip())))
del raw_data
data = sorted(data, key=lambda tup: tup[0])

middle_point = ceil(lever_length / 2)
forces = calculate_forces(middle_point)
difference = abs(forces[0] - forces[1])

print('Forces at middle point: %s and %s' % forces)

if forces[0] == forces[1]:
    print('We have found the fulcrum point on the first try.')
elif forces[0] > forces[1]:
    print('Fulcrum is to the left.')
    recurse(middle_point, -1, difference)
else:
    print('Fulcrum is to the right.')
    recurse(middle_point, 1, difference)
