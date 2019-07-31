function [] = plotBoxApproachStreamSizeAll(ds1,ds2,ds3,ds4,batchsizes,titlelabel,filename, approach1label, approach2label, approach3label, approach4label,ylabeltext, xlabeltext)

    clf;
    set(0,'DefaultFigureVisible','off');
    
    f = figure;
    p = uipanel('Parent',f,'BorderType','none'); 
    p.Title = titlelabel;
    p.TitlePosition = 'centertop'; 
    p.FontSize = 9;
    p.FontWeight = 'bold';
    
    ds1 = ds1';
    ds2 = ds2';
    ds3 = ds3';
    ds4 = ds4';
    batchsizes = batchsizes';
    
    dst = vertcat(ds1,ds2,ds3,ds4);
    ytickmin = min(dst(:,1));
    for i = 1: size(dst,2)
        if ytickmin > min(dst(:,1))
            ytickmin = min(dst(:,1));
        end
    end
    ytickmax = max(dst(:,1));
    for i = 1: size(dst,2)
        if ytickmax < min(dst(:,1))
            ytickmax = min(dst(:,1));
        end
    end
    
    
    subplot(2,2,1, 'Parent', p);
    plotBoxApproachStreamSize(ds1,batchsizes,ytickmin,ytickmax, approach1label, ylabeltext,xlabeltext);
    
    subplot(2,2,2, 'Parent', p);
    plotBoxApproachStreamSize(ds2,batchsizes,ytickmin,ytickmax,approach2label, ylabeltext,xlabeltext);
    
    subplot(2,2,3, 'Parent', p);
    plotBoxApproachStreamSize(ds3,batchsizes,ytickmin,ytickmax,approach3label, ylabeltext,xlabeltext);
    
    
    subplot(2,2,4, 'Parent', p);
    plotBoxApproachStreamSize(ds4,batchsizes,ytickmin,ytickmax, approach4label, ylabeltext,xlabeltext);
    print(filename, '-dpng');
    clf;

end

