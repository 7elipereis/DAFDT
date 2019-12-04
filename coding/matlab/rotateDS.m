function [ RDS ] = rotateDS( DS, angle )
%ROTATEDS Summary of this function goes here
%   Detailed explanation goes here
% define the x- and y-data for the original line we would like to rotate
x = DS(:,1)';
y = DS(:,2)';
% create a matrix of these points, which will be useful in future calculations
v = [x;y];
% choose a point which will be the center of rotation
x_center = 0;
y_center = 0;
% create a matrix which will be used later in calculations
center = repmat([x_center; y_center], 1, length(x));
% define a 60 degree counter-clockwise rotation matrix
%theta = pi/4;       % pi/3 radians = 60 degrees
theta = deg2rad(single(angle));
%theta = 0.349066; 
R = [cos(theta) -sin(theta); sin(theta) cos(theta)];
% do the rotation...
s = v - center;     % shift points in the plane so that the center of rotation is at the origin
so = R*s;           % apply the rotation about the origin
vo = so + center;   % shift again so the origin goes back to the desired center of rotation
% this can be done in one line as:
% vo = R*(v - center) + center
% pick out the vectors of rotated x- and y-data
x_rotated = vo(1,:);
y_rotated = vo(2,:);
% make a plot
%plot(x, y, 'k-', x_rotated, y_rotated, 'r-', x_center, y_center, 'bo');
xs = size(x_rotated);
xs = xs(2);
A = zeros(1);
B = zeros(1);
C = zeros(1);


for i=1:xs
    if x_rotated(1,i)>=20 && x_rotated(1,i)<=80 && y_rotated(1,i)>=20 && y_rotated(1,i)<=80
        A = horzcat(A,x_rotated(1,i));
        B = horzcat(B,y_rotated(1,i));
        C = horzcat(C,DS(i,3));
        %x_rotated(1,i) = normDS(x_rotated(1,i),0,100);
    end
end
ys = size(y_rotated);
ys = ys(2);
for i=1:ys
    if y_rotated(1,i)<0 || y_rotated(1,i)>100
        %y_rotated(1,i) = normDS(y_rotated(1,i),0,100);
    end
end
%DS2 = [A' B' C'];
%DS2 = DS2([2:end],:);

DS2 = [x_rotated' y_rotated' DS(:,3)];

%gscatter(DS2(:,1),DS2(:,2),DS2(:,3),['r', 'b'] ,'.',10);
%hold on;
%%gscatter(DS(:,1),DS(:,2),DS(:,3),['r', 'b'] ,'.',10);
%xlabel('A');
%ylabel('B');

RDS = DS2;
clear x;
clear y;
clear v
clear x_rotated;
clear y_rotated;
clear A;
clear B;
clear C;
clear DS2;
clearvars -global
end

